# Calendar App Java Spring REST API

## Dev Start
- Install homebrew - https://brew.sh/
- Install sdkman - https://sdkman.io/install

## Java Version
- Use Java 17, local for now but will dockerise LTS version later
- In your terminal, run _**sdk list java**_
- Choose a version of Java 17 compatible with your system _(Recommend Temurin or Zulu)_
- Copy the **Identifier** in the last column for your chosen Java version
- Hit **_Q_** to quit the _**sdk list**_ function
- Run _**sdk install java**_ with your chosen version identifier argument - eg **_sdk install java 17.0.10-tem_**

## Usage
- Run the application main method
- View the logs to confirm it starts up on port 8080
- In a browser, head to http://localhost:8080/api/hello
- Also try with a name parameter http://localhost:8080/api/hello?name={myName}

## Contributing
- Use git and GitHub to contribute, follow these steps to get set up -
- https://docs.github.com/en/authentication/connecting-to-github-with-ssh/checking-for-existing-ssh-keys
- https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent
- https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account

## Workflow
- Clone repo / pull changes
- Create new local dev feature branch
- Make feature code changes in IDE of choice _(IntelliJ recommended)_
- Commit changes to local dev feature branch
- Push to remote feature branch
- Create PR to merge remote dev feature branch into main