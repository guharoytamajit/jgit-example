package com;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class Push {
	public static void main(String[] args) throws Exception {
		File repo = new File("repo");
		Git git = null;
		try {
			git = Git.open(repo);
		} catch (Exception e) {
			// $git init #if repo is not a git repository
			git = Git.init().setDirectory(repo).call();
		}
//		Status status = git.status().call();
//		if(status.hasUncommittedChanges()){ git.stashCreate().call(); } 
		git.add().addFilepattern(".").call();
		git.commit().setMessage("test commit: " + new Date()).call();
		git.push().setRemote("https://github.com/guharoytamajit/repo.git")
				.setCredentialsProvider(new UsernamePasswordCredentialsProvider("gitusename", "gitpassword")).call();

	}
}
