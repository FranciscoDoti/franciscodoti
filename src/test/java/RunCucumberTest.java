import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.google.common.eventbus.AllowConcurrentEvents;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import pages.CommonMethods;
import utils.Token;

import javax.annotation.concurrent.ThreadSafe;


@RunWith(Cucumber.class)

@ThreadCount(5)

@CucumberOptions(

        features = "src/test/resources/featuresToRun",
        glue = "classpath:QAAutomation.stepDefinitions",
        plugin = { "pretty","html:target/cucumber", "json:target/cucumber.json"}
        )



public class RunCucumberTest {


}
