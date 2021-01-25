dotnet tool install -g dotnet-aspnet-codegenerator

dotnet tool update -g dotnet-aspnet-codegenerator

dotnet add package Microsoft.VisualStudio.Web.CodeGeneration.Design

dotnet aspnet-codegenerator controller -name ProcessController -outDir Controllers

dotnet aspnet-codegenerator view Index Empty -udl -outDir Views/Process

============================== Azure ============================
Created a website on Azure
Deployment Center >> Deployed local git
Created deployment credentials

git init
git add .
git commit -m "1st commit"
git remote add az https:.......git
git push az master

