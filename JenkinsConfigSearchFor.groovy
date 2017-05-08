// Replace the <SEARCH_TEXT> with the work you are looking for

Jenkins.instance.getAllItems().each{
  if(it.getFullName().contains('AHS_IPOC')){
    def cf = it.getConfigFile().asString()
    
    if(cf.toLowerCase().contains('<SEARCH_TEXT>')){      
      println it.getAbsoluteUrl()
    }
  }
}

println ""
