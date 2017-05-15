println 'Job Name,Url,JDK'

Jenkins.instance.getAllItems(AbstractProject.class).each{
  if(it.hasProperty('JDK') && null!=it.getJDK() && '' != it.getJDK().getName().trim()){
    println it.getFullName() + "," + it.getAbsoluteUrl() + "," + it.getJDK().getName()
  }
}

println ''
