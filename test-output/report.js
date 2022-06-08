$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:Features/Skill_API/SkillAPI_Get_All_Skills.feature");
formatter.feature({
  "name": "To verify API automation with rest assured",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "To verify Rest Service - Get Method",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "User want to execute GET opetaion for \"/Skills\"",
  "keyword": "Given "
});
formatter.match({
  "location": "SkillAPI_Get_All_Skills_Steps.user_want_to_execute_GET_opetaion_for(String)"
});
formatter.result({
  "error_message": "java.lang.ExceptionInInitializerError\r\n\tat org.codehaus.groovy.reflection.ClassInfo.isValidWeakMetaClass(ClassInfo.java:283)\r\n\tat org.codehaus.groovy.reflection.ClassInfo.getMetaClassForClass(ClassInfo.java:253)\r\n\tat org.codehaus.groovy.reflection.ClassInfo.getMetaClass(ClassInfo.java:309)\r\n\tat io.restassured.authentication.NoAuthScheme.$getStaticMetaClass(NoAuthScheme.groovy)\r\n\tat io.restassured.authentication.NoAuthScheme.\u003cinit\u003e(NoAuthScheme.groovy)\r\n\tat io.restassured.RestAssured.\u003cclinit\u003e(RestAssured.java:354)\r\n\tat stepDefinitions.Skills.API.SkillAPI_Get_All_Skills_Steps.user_want_to_execute_GET_opetaion_for(SkillAPI_Get_All_Skills_Steps.java:26)\r\n\tat ✽.User want to execute GET opetaion for \"/Skills\"(file:Features/Skill_API/SkillAPI_Get_All_Skills.feature:4)\r\nCaused by: groovy.lang.GroovyRuntimeException: Conflicting module versions. Module [groovy-xml is loaded in version 4.0.1 and you are trying to load version 3.0.8\r\n\tat org.codehaus.groovy.runtime.metaclass.MetaClassRegistryImpl$DefaultModuleListener.onModule(MetaClassRegistryImpl.java:534)\r\n\tat org.codehaus.groovy.runtime.m12n.ExtensionModuleScanner.scanExtensionModuleFromProperties(ExtensionModuleScanner.java:87)\r\n\tat org.codehaus.groovy.runtime.m12n.ExtensionModuleScanner.scanExtensionModuleFromMetaInf(ExtensionModuleScanner.java:81)\r\n\tat org.codehaus.groovy.runtime.m12n.ExtensionModuleScanner.scanClasspathModulesFrom(ExtensionModuleScanner.java:63)\r\n\tat org.codehaus.groovy.runtime.m12n.ExtensionModuleScanner.scanClasspathModules(ExtensionModuleScanner.java:54)\r\n\tat org.codehaus.groovy.runtime.metaclass.MetaClassRegistryImpl.\u003cinit\u003e(MetaClassRegistryImpl.java:133)\r\n\tat org.codehaus.groovy.runtime.metaclass.MetaClassRegistryImpl.\u003cinit\u003e(MetaClassRegistryImpl.java:94)\r\n\tat groovy.lang.GroovySystem.\u003cclinit\u003e(GroovySystem.java:37)\r\n\tat org.codehaus.groovy.reflection.ClassInfo.isValidWeakMetaClass(ClassInfo.java:283)\r\n\tat org.codehaus.groovy.reflection.ClassInfo.getMetaClassForClass(ClassInfo.java:253)\r\n\tat org.codehaus.groovy.reflection.ClassInfo.getMetaClass(ClassInfo.java:309)\r\n\tat io.restassured.authentication.NoAuthScheme.$getStaticMetaClass(NoAuthScheme.groovy)\r\n\tat io.restassured.authentication.NoAuthScheme.\u003cinit\u003e(NoAuthScheme.groovy)\r\n\tat io.restassured.RestAssured.\u003cclinit\u003e(RestAssured.java:354)\r\n\tat stepDefinitions.Skills.API.SkillAPI_Get_All_Skills_Steps.user_want_to_execute_GET_opetaion_for(SkillAPI_Get_All_Skills_Steps.java:26)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:26)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:20)\r\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:57)\r\n\tat cucumber.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:50)\r\n\tat cucumber.runner.TestStep.executeStep(TestStep.java:65)\r\n\tat cucumber.runner.TestStep.run(TestStep.java:50)\r\n\tat cucumber.runner.PickleStepTestStep.run(PickleStepTestStep.java:43)\r\n\tat cucumber.runner.TestCase.run(TestCase.java:46)\r\n\tat cucumber.runner.Runner.runPickle(Runner.java:50)\r\n\tat cucumber.runtime.junit.PickleRunners$NoStepDescriptions.run(PickleRunners.java:146)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:68)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:23)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\r\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:73)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:126)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:66)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\r\n\tat cucumber.api.junit.Cucumber$RunCucumber.evaluate(Cucumber.java:156)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\r\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:93)\r\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:40)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:529)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:756)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:452)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:210)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "User submit the GET request",
  "keyword": "When "
});
formatter.match({
  "location": "SkillAPI_Get_All_Skills_Steps.user_submit_the_GET_request()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User should get 200 success Status code",
  "keyword": "Then "
});
formatter.match({
  "location": "SkillAPI_Get_All_Skills_Steps.user_should_get_success_Status_code(Integer)"
});
formatter.result({
  "status": "skipped"
});
});