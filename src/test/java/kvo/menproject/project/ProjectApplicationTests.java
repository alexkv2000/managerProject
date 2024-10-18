package kvo.menproject.project;

import kvo.menproject.project.entity.libDivision;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProjectApplicationTests {
//    https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html
    @Test
    void contextLoads() {
        String actual = "FEST-Assert";
        assertThat(actual)
                .isNotNull()
                .as("Satart = FEST", actual.startsWith("FEST"))
                .endsWith("Assert")
                .contains("-")
                .hasSize(11);
    }

}
