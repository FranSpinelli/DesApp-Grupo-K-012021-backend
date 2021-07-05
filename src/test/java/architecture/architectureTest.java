package architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class architectureTest {

    private JavaClasses classes;

    @BeforeEach
    public void init() {
        ImportOption ignoreTests = (Location location) -> !location.contains("/test/");
        classes = new ClassFileImporter().withImportOption(ignoreTests)
                .importPackages("ar.edu.unq.desapp.grupoK.backenddesappapi");
    }

    @Test
    public void testServicesNameEndingWithService_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..service..")
                .and().resideOutsideOfPackage("..serviceLevelExceptions..")
                .and().resideOutsideOfPackage("..serviceHelpers..")
                .should().haveSimpleNameEndingWith("Service");

        rule.check(classes);
    }

    @Test
    public void testServicesHaveServiceAnnotation_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..service..")
                .and().resideOutsideOfPackage("..serviceLevelExceptions..")
                .and().resideOutsideOfPackage("..serviceHelpers..")
                .and().haveSimpleNameNotContaining("Abstract")
                .should().beAnnotatedWith(Service.class);

        rule.check(classes);
    }

    @Test
    public void testControllersNameEndingWithController_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..webService..")
                .and().resideOutsideOfPackage("..apiKey..")
                .and().resideOutsideOfPackage("..dto..")
                .should().haveSimpleNameEndingWith("Controller");

        rule.check(classes);
    }

    @Test
    public void testControllersHaveRestControllerAnnotation_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..webService..")
                .and().resideOutsideOfPackage("..apiKey..")
                .and().resideOutsideOfPackage("..dto..")
                .should().beAnnotatedWith(RestController.class);

        rule.check(classes);
    }

    @Test
    public void testRepositoriesNameEndingWithRepository_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..persistence..")
                .should().haveSimpleNameEndingWith("Repository");

        rule.check(classes);
    }

    @Test
    public void testRepositoriesHaveRepositoryAnnotation_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..persistence..")
                .should().beAnnotatedWith(Repository.class);

        rule.check(classes);
    }

}
