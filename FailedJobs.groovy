import groovy.time.TimeCategory
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*


def jen = Jenkins.instance

allJobs = jen.getAllItems().findAll{ job ->
	(job instanceof Job) && !job.isDisabled()
}
def output = ""

long secondInMillis = 1000;
long minuteInMillis = secondInMillis * 60;
long hourInMillis = minuteInMillis * 60;
long dayInMillis = hourInMillis * 24;

long duration = minuteInMillis

for(Job job : allJobs)
{ 

	if(job.getFullName().contains("AHS")){
		continue
	}
	
	 build=job.getLastBuild()
	 
	 if(null==build){
		 continue;
	 }
	 
	  def builtOnServer = build.builtOn
	  def builtOn = ""
	  
	  if(builtOnServer instanceof Slave){
		builtOn = builtOnServer.name
	  }else{
		builtOn = builtOnServer.getDisplayName()
	  }		  

		action = build.getAction(jenkins.metrics.impl.TimeInQueueAction.class)

		def totalDuration = ""
		def totalQDuration = ""
		def totalBuildDuration = ""
		def totalJobDuration = ""

		if (action != null) {
			totalDuration = String.format( "%02.02f", (action.getTotalDurationMillis() / duration))
			totalQDuration = String.format( "%02.02f", (action.getQueuingDurationMillis() / duration))
			totalBuildDuration = String.format( "%02.02f", (action.getBuildingDurationMillis() / duration))
			//totalJobDuration  = totalDuration + totalQDuration
		}

		def lastSuccessfullBuildTime = (job.getLastSuccessfulBuild()!=null) ? job.getLastSuccessfulBuild().getTime().format("MM-dd-yyyy HH:mm:ss") : ""
		def lastBuildTime = (build.getTime() !=null ? build.getTime().format("MM-dd-yyyy HH:mm:ss") : "")
		def lastBuildResult = build.result
		def lastBuild = build

		def displayName = job.getFullName()
		def jobUrl = job.getAbsoluteUrl()	
		def comment = ""		
		
		if(lastBuild.result == Result.FAILURE){						
			def lastFailedBuild = lastBuild

			 output+= (displayName + "," + build.getAbsoluteUrl()+ "console" + "," + builtOn + "," + lastBuild.result + "," + lastBuildTime + ","+ lastSuccessfullBuildTime + "," + totalQDuration + "," + totalBuildDuration + "," + totalDuration + "," + category + "," + comment +"\n")
			
		}else{
			//output+= (displayName + "," + build.getAbsoluteUrl()+ "console" + "," + builtOn + "," + lastBuild.result + "," + lastBuildTime + ","+ lastSuccessfullBuildTime + "," + totalQDuration + "," + totalBuildDuration + "," + totalDuration + "," + "," + "," + "\n")			
		}
}


println "Job,Build Url,Built On,Status,Build Time,Last Success,Q Duration (Min),Build Duration (Min),Total Build Duration (Min)"
println output
