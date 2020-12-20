import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-html-report"}, // подключаем плагин который будет сохранять результаты тестирования в виде html
        features = {"classpath:features"},  //  указываем где хранятся файлы с описанием тестовых сценариев
        glue = {"ru.abtankshop.steps"},   // в каком пакете находятся код преобразующий сценарии в реальные действия
        snippets = SnippetType.CAMELCASE)   //  snippets связывает сценарий с реальными действиями
public class LaunchTest {

}
