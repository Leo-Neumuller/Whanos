folder('Whanos base images') {
  description('Whanos base images')
}
folder('Projects') {
  description('Projects')
}

freeStyleJob('Whanos base images/whanos-befunge') {
  steps {
    shell('docker build . -f /usr/local/images/befunge/Dockerfile.base -t whanos-befunge')
  }
}

freeStyleJob('Whanos base images/whanos-c') {
  steps {
    shell('docker build . -f /usr/local/images/c/Dockerfile.base -t whanos-c')
  }
}

freeStyleJob('Whanos base images/whanos-java') {
  steps {
    shell('docker build . -f /usr/local/images/java/Dockerfile.base -t whanos-java')
  }
}

freeStyleJob('Whanos base images/whanos-javascript') {
  steps {
    shell('docker build . -f /usr/local/images/javascript/Dockerfile.base -t whanos-javascript')
  }
}

freeStyleJob('Whanos base images/whanos-python') {
  steps {
    shell('docker build . -f /usr/local/images/python/Dockerfile.base -t whanos-python')
  }
}

freeStyleJob('Whanos base images/Build all base images') {
    publishers {
        downstream('Whanos base images/whanos-c', 'SUCCESS')
        downstream('Whanos base images/whanos-python', 'SUCCESS')
        downstream('Whanos base images/whanos-javascript', 'SUCCESS')
        downstream('Whanos base images/whanos-befunge', 'SUCCESS')
        downstream('Whanos base images/whanos-java', 'SUCCESS')
    }
}

freeStyleJob('link-project') {
  parameters {
    stringParam('GITHUB_NAME', null, 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
    stringParam('DISPLAY_NAME', null, 'Display name for the job')
  }
  steps {
    dsl {
      text('''freeStyleJob('Projects/' + DISPLAY_NAME) {
          wrappers {
            preBuildCleanup {
                includePattern('**/target/**')
                deleteDirectories()
                cleanupParameter('CLEANUP')
            }
          }
          triggers {
              scm('* * * * *')
          }
          scm {
              github(GITHUB_NAME)
          }
          steps {
            shell('chmod +x /usr/local/ImageDeployment.sh')
            shell('bash -c "/usr/local/ImageDeployment.sh"')
          }
        }
      ''')
    }
  }
}