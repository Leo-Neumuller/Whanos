import io.jenkins.plugins.casc.*
import jenkins.model.*
import groovy.json.JsonOutput

CasCGlobalConfig config = GlobalConfiguration.all().get(CasCGlobalConfig.class);

Boolean changed = false
Boolean failed = false
String msg = "Success"

String newConfigPath = "$jcasc_path"

if(config != null) {
  String currentConfigPath = config.getConfigurationPath()
  if (currentConfigPath != newConfigPath) {
    changed = true
    config.setConfigurationPath(newConfigPath)
    config.save()
  }
}

// reload configuration when new config path is detected
if (changed) {
  try {
    ConfigurationAsCode.get().configure()
  } catch (Exception ex) {
    // set failed to yes
    failed = true
    msg = ex.toString()
  }
}

def json = JsonOutput.toJson([
  changed: changed,
  failed: failed,
  msg: msg
])

return json.toString()