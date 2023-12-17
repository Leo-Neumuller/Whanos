import groovy.json.JsonOutput
import io.jenkins.plugins.casc.*

CasCGlobalConfig config = GlobalConfiguration.all().get(CasCGlobalConfig.class)
String currentConfigPath = config.getConfigurationPath()

String msg = "Success"
Boolean failed = false

if (currentConfigPath != null) {
  try {
    ConfigurationAsCode.get().configure()
  } catch (Exception ex) {
    // set failed to yes
    failed = true
    msg = ex.toString()
  }
} else {
  // fail because no config path is set
  failed = true
  msg = "JCasC as currently no configuration path set! Reloading configuration skipped. Make sure to set a correct configuration path!"
}



def json = JsonOutput.toJson([
  failed: failed,
  msg: msg
])

return json.toString()