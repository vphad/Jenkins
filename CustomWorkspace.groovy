/*
* Jenkins script to get custom workspace for all the jobs
* Run this script on the jenkins groovy console
*
*/

Jenkins.instance.getAllItems().each{
  if(it.hasProperty("customWorkspace") && it.getCustomWorkspace()!=null){
  	println "${it.getAbsoluteUrl()}configure,${it.customWorkspace}"
  }
}

println ""
