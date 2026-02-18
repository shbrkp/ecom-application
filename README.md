Git Commands

Step 1: 
git init
// This creates a hidden .git directory to track changes
Step 2:
git add .
//  The . command adds all files in the current directory to the staging area. You can use a .gitignore file to exclude specific files (like sensitive data or build artifacts).
Step 3:
git commit -m "Initial commit"
// Commit your staged files to your local repository:
// The -m flag allows you to provide a descriptive message for your commit.
Step 4:
git remote add origin [remote_repository_url]
// Link your local repository to the remote repository you created online:
// Replace [remote_repository_url] with the URL you copied earlier. origin is the default name given to the remote server.

Step 5:
// Push your code to the remote repository
git push -u origin main
// git push uploads your local commits to the remote repository.
// The -u flag sets the remote origin as the upstream branch. This allows you to use git push and git pull without specifying the remote and branch name in the future.
// The default branch name may be main or master depending on your setup; use the correct name. 


git checkout -b <branch-name>

git status  
-- to get the chnged files

git add .

git commit -m "testbranch changes commit to local"

git push --set-upstream origin testbranch

remote: Create a pull request for 'testbranch' on GitHub by visiting:
remote:      https://github.com/shbrkp/ecom-application/pull/new/testbranch
