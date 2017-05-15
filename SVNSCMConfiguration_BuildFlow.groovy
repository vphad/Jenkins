// This script identifies the svn scm configuration of Jenkins jobs for BuildFlow type of projects.

Jenkins.instance.getAllItems(com.cloudbees.plugins.flow.BuildFlow).each{
  if (it.hasProperty('scm') ){
    def scm = it.getScm()
    
    if(null!=scm && scm instanceof hudson.scm.SubversionSCM){
      
      scm.locations.each{
        loc -> 
        
        if('empty'!=loc.getDepthOption()){
          println it.getAbsoluteUrl()
        }
      }
    
  }
}
}
