Initial Setup
----------------------------------
1. Clone repository
2. Make sure to have `develop` branch locally and remotely
3. `main` branch can be created but not needed yet.



Starting a new Feature (Workflow)
----------------------------------
1. Locally, switch to `develop` branch
    ```shell
    git checkout develop
    ```
2. Make sure your local `develop` branch is upto date with the remote
    ```shell
    git pull
    ```
3. Ready to start a feature. Create your feature branch and switch to it
    ```shell
    git checkout -b feature-1.1/menu-screen
    ```
4. Build your feature (finish work locally), Add, commit, and push
Make changes: Create new files, edit files, etc...
Should repeat the following (small increments):
    ```shell
    git add <your-files>
    git commit -m "your message"
    ```
5. Push the changes to the remote feature branch so others can review your work later through pull requests.
    ```shell
    git push
    ```


Pull Request: Reviewing Feature 
----------------------------------
1. Make sure your feature branch is pushed remotely to github.
2. Go to github, and create your Pull Request for the feature branch.
3. When creating a Pull Request, add summary, description, reviewers, etc...
4. When changes are requested by the reviewers, make your changes locally then commit, push, and request for a re-review.
5. Once Pull Request is approved, on github the owner of the pull request will click the big MERGE to `develop` button.
6. After merge, you can start a new feature.