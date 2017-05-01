/*
 Below script lists all the builds from Jenkins executed between from & to date. 
 
 Please change the hardcoded dates in 'from' and 'to' fields
  */

import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*


def jen = Jenkins.instance

allJobs = jen.getAllItems().findAll{
	job ->
	(job instanceof Job) && (job.hasProperty("disabled") && !job.isDisabled())
}

from = new Date().parse("MM/dd/yyyy HH:mm:ss", "04/30/2017 22:00:00")
to = new Date().parse("MM/dd/yyyy HH:mm:ss", "05/01/2017 02:00:00")

println allJobs.size()

for(Job job : allJobs) {
	builds = job.getBuilds().byTimestamp(from.getTime(), to.getTime())
  
  	if(builds.size() > 0) {
      for(def build: builds){
		  println "${job.getFullName()},${build.getTimestamp().format("MM-dd-yyyy HH:mm:ss")},${build.getAbsoluteUrl()}"
      }
	}
}
