package icd9.services;

import icd9.classification.*;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Mariusz Szymanski
 */
@Singleton
public class ICDBuilder {

    private List<Record> records = new ICDClient().getICDRecords();

    public Classification getAllClassification() {
        return new Classification(this.getDistinctSections());
    }

    private List<Section> getDistinctSections() {

        return records.stream()
                .filter(distinctByKey(Record::getSection))
                .collect(Collectors.toList())
                .stream().map(record -> new Section(
                        record.getSectionNumber(),
                        record.getSection(),
                        this.getDistinctSubsections(record.getSectionNumber())))
                .collect(Collectors.toList());
    }

    private List<Subsection> getDistinctSubsections(String section) {

        return records.stream()
                .filter(e -> Objects.equals(e.getSectionNumber(), section))
                .filter(distinctByKey(Record::getSubsection))
                .collect(Collectors.toList())
                .stream().map(record -> new Subsection(
                        record.getSubsectionNumber(),
                        record.getSubsection(),
                        this.getDistinctMainCategories(record.getSubsectionNumber())))
                .collect(Collectors.toList());
    }

    private List<MainCategory> getDistinctMainCategories(String subsectionNumber) {

        return records.stream()
                .filter(e -> Objects.equals(e.getSubsectionNumber(), subsectionNumber))
                .filter(distinctByKey(Record::getMainCategory))
                .collect(Collectors.toList())
                .stream().map(record -> new MainCategory(
                        record.getMainCategoryNumber(),
                        record.getMainCategory(),
                        this.getDistinctDetailedCategories(record.getMainCategoryNumber())))
                .collect(Collectors.toList());
    }

    private List<DetailedCategory> getDistinctDetailedCategories(String mainCategoryNumber) {

        return records.stream()
                .filter(e -> Objects.equals(e.getMainCategoryNumber(), mainCategoryNumber))
                .collect(Collectors.groupingBy(Record::getMainCategoryNumber, Collectors.toList()))
                .get(mainCategoryNumber)
                .stream().map(r -> new DetailedCategory(r.getDetailedCategoryNumber(), r.getDetailedCategory()))
                .collect(Collectors.toList());
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
