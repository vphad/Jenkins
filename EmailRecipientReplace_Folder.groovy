import hudson.tasks.Mailer

// Replaces the email recipients at folder level

item = Jenkins.instance.getItemByFullName('<jobPath>')

// item = Jenkins.instance.getItemByFullName('ABCD/XYZ/JKL')

it1.getItems().each{ job ->
	jobName = job.getFullName();
  	if(job.publishersList !=null)
	  
	  for(publisher in job.publishersList) {
  		if(publisher instanceof Mailer) {
            	publisher.recipients="Vijayanand.Phad-non-empl@moodys.com"
            
  			println "${job.getAbsoluteUrl()}configure,${publisher.recipients}"          
  		}
	  } 
}


println ""
