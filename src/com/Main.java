package com;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.RemoteListCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.lib.Ref;

public class Main {
public static void main(String[] args) throws Exception {
	File repo = new File("repo");
	//git init
//	Git git = Git.init().setDirectory( repo ).call();
	//use existing git repo
	Git git = Git.open( repo );
	Status status = git.status().call();
	
	if(status.hasUncommittedChanges()){
		git.stashCreate().call();
	}
	PullResult pullResult = git.pull().call();
	if(!pullResult.isSuccessful()){
		System.out.println("git pull failed");
	}
	 List<org.eclipse.jgit.transport.RemoteConfig> call = git.remoteList().call();
 List<Ref> call2 = git.branchList().setListMode(ListMode.ALL).call();
// Ref ref = call2.get(0);
// ref.getObjectId().
// CheckoutCommand checkout = git.checkout();
	System.out.println(git);
//	git.checkout().setName("origin/test2").call();
	//git clone
//	Git git = Git.cloneRepository()
//			  .setURI( "https://github.com/guharoytamajit/propertyFiles.git" )
//			  .setDirectory( repo )
//			  .call();
	Ref ref = git.checkout().
	        setCreateBranch(true).
	        setName("test2").
	        setUpstreamMode(SetupUpstreamMode.TRACK).
	        setStartPoint("origin/" + "test2").
	        call();
}
}
