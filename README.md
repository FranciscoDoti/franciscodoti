# TESTING AUTOMATION CI Full Circuit

##### Frameworks and tools used:

###### API Testing 

- Rest Assured (for API testing)
- Cucumber (as an integrated tool with the Page Object Model design pattern)
- JUnit (Test Runner)
- Jenkins. Plugins used: 
	- Build monitor view
	- Cucumber Reports
	- Build Failure Analyzer
	- Jenkins Text Finder

###### Performance, Load, Stress Testing

 - JMeter
 - Docker
 - Portainer ( It's a Docker UI)
 - InfluxDB
 - Grafana

###### UI Testing

 - Protractor (for Angular apps)
 - Selenium WebDriver. (an alternative for non angular apps)

		Note: UI testing can be combined with test deployment tools in multiple OSs and Browsers. Some of the ones I recommend are: Applitools, BrowserStack or SauceLabs
		
		Note: We haven't started to do UI Testing yet. It's a pending task. 
		Important recommendation: Only do UI Testing in advanced stages of a project and for a stable application.


# API Testing

		Note: All the API testing circuit we are doing in a programmatic way, using the previously mentioned tools.


### Page Object Model

##### The design pattern we use consists in dividing the tests into functionalities. In turn, for each functionality, we created 3 different files:
	Features --> Where we write in Gherkin format (Given, When, Then), all the scenarios of each functionality.
	StepDefinitions --> It is a proper class, and here, using the Cucumber notations, we define the behavior of each step previously written in the ".feature" file.
	Pages --> Here we define the methods that are invoked from the StepDefinitions classes.

###### FEATURES

For more information about how to write the scenarios, follow the following guide:
https://docs.cucumber.io/gherkin/reference/.

The main components of a feature are:
				- Scenarios
				- ScenarioOutline
				- Background: Everything defined in Background is what will be executed first and the execution will be repeated in all scenarios.
				- Tags: The features may have tags, which JUnit can then read, to execute only the features corresponding to the tag we choose.
				
				

###### STEP DEFINITIONS

For more information about how to write stepDefinitions, follow the following guide:
https://docs.cucumber.io/cucumber/step-definitions/


###### PAGES

In the classes "pages", we define and implement the methods of the code that will be called from the stepDefinitions.

#####Integración entre JUnit y Cucumber

So that JUnit, which is in charge of running the tests, recognizes Cucumber, we have to have a class, called "RunCucumberTest" (IT IS IMPORTANT THAT YOU HAVE THIS NAME, SINCE BUT JUnit DOES NOT RECOGNIZE IT), with the following code:

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
    
            features = "src/test/resources/podrolautomationv3", ///HERE WE DEFINE THE ROUTE WHERE ALL THE FEATURE FILES ARE FOUND.
            glue = "classpath:podrolautomationv3.stepDefinitions", /// HERE WE DEFINE THE ROUTE WHERE ALL THE CLASSES OF STEP DEFINITIONS ARE FOUND.
            plugin = { "pretty","html:target/cucumber", "json:target/cucumber.json"} /// HERE, WITH THESE PLUGINS, WE CAN MAKE A GENERATION IN "target / cucumber.json" the report that we will then configure and visualize from Jenkins
            )
    
    
    
    public class RunCucumberTest {
    
    
    }


## Reports

	Jenkins monitor: https://value-capture-jenkins.platforms.engineering/view/DEV%20Integration%20Tests%20Monitor/
	Cucumber Report: You can enter a build of any job that has the cucumber report plugin configured, and from there, access the Cucumber report. For example: https://value-capture-jenkins.platforms.engineering/job/pod/job/stark/job/Automation/job/DEV/job/FIXED%20VOLUME/1057/cucumber-html-reports/overview-features.html
	
## Reports settings

### Jenkins Monitor:
	Once inside the monitor, click on the configuration icon and then on Configure. A configuration page will open, from which you can select the Jenkins jobs that you want to display on the monitor.

### Cucumber Report:

The configuration must be done for each Jenkins job, as follows.

1) In the "Post Build Actions" section of each job, add the "Jenkins Text Finder" plugin.
	In the Files field, complete with the following: "target / surefire-reports / RunCucumberTest.txt", and in the RegularExpression field, enter the following: "^. * FAILURE. * $". Then, check the options: "Also search the console output" and "Unstable if found". This is so that if it is found in the console that there is a failure, the build is in unstable and in the monitor it is displayed in orange. Then, with the plugin that comes next, we will configure from how many failed tests, it will have to pass to failed and, consequently, to network, in the monitor.

2)  Add the "Cucumber Reports" plugin in the "Post Build Actions" section. Be sure to place it under the "Jenkins Text Finder" plugin, so that it runs after it. The configuration should contain the following:

	File include: **/cucumber.json
	Number of failed steps: 9999
	Number of skipped steps: 9999
	Number of pending steps	: 9999
	Number of undefined steps: 9999
	Number of failed scenarios: 10 (We define this number here. It is the number of tests from which we want the monitor to change from red to orange).
	Number of failed features: 9999
	Build Status: Failure.


# Performance, Load and Stress Testing


		Note: In this case, we are not working in a programmatic way, but from the Jmeter UI. The idea is that we are going to assemble the tests in the Jmeter UI, then we will send the results to InfluxDB, and then, we will use the Influx data to build Dashboards with Grafana.
		

###Setup JMeter

	brew install jmeter
	jmeter

	Note: The Jmeter UI will open, from which the tests can be started.

#### Main Components of a Jmeter test

	ThreadGroup
		HeaderManager
		HTTP Request
		Summary Report
		View Results Tree
		Backend Listener
		
###Backend Listener

	Note: The configuration included in the backend listener, will be in charge of sending the results to InfluxDB, whose configuration we will take care of below, and then InfluxDB to Grafana.
-- config
	
	 influxDBMetricsSender: org.apache.jmeter.visualizers.backend.influxdb.HttpMetricsSender
	 influxDbUrl: http://localhost:8086/write?db=jmeter
	 Application: "Here I suggest to put the same name as the ThreadGroup".
	 measurement: jmeter
	 summaryOnly: false
	 samplersRegEx: .*
	 percentiles: 99;95;90
	 testTitle: Test name
	 eventTags: 


###Setup InfluxDB

	Note: We can raise influence in server mode or Browser mode. I recommend to raise it in server mode, since it is easier to run queries.
--
Run next commands:

	brew update
	brew install influxdb
	ln -sfv /usr/local/opt/influxdb/*.plist ~/Library/LaunchAgents
	launchctl load ~/Library/LaunchAgents/homebrew.mxcl.influxdb.plist
	influxd -config /etc/influxdb/influxdb.conf
	chown influxdb:influxdb /mnt/influx
	chown influxdb:influxdb /mnt/db

Then, to execute influx, execute the following command from the terminal:

	influx

This should appear:

	Connected to http://localhost:8086 version v1.7.4
	InfluxDB shell version: v1.7.4
	Enter an InfluxQL query
	> 


Now, we create a db called Jmeter, with the following command:

	CREATE DATABASE jmeter

Then, to use this db, we enter with the following command:

	use jmeter

We should leave something like this:

	> use jmeter
	Using database jmeter
	> 

Here we can run queries, to verify that the information has arrived from Jmeter:

	Test running "select * from jmeter", after having executed some jmeter test that has the backend listener configured. Verify that it brings results.

### Setup Portainer

	docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer

From the browser, then, access "localhost: 9000". Verify that a Docker graphical interface will open, called Portainer.

### Setup Grafana

	Precondition: have docker installed in the terminal.

Create a Grafana container with the following command:

	docker run -d --name=grafana -p 3000:3000 grafana/grafana

From the browser, then, access "localhost: 3000". Verify that a Grafana graphical interface will open.

Then, follow the steps of Grafana, and add as DataSource to the InfluxDB service that we had raised from the terminal in previous steps.

Finally, start putting together Dashboards of all kinds.


Done by Francisco Doti Texeira (Stark team)
