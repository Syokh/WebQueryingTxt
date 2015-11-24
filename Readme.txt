This is maven project so all needed libraries are set up automatically (using dependecy in the pom.xml)
The war file is attached, but you can generate your own one. (mvn clean, mvn install)
You can run the application through the tomcat, using war file or from the IDEA.

The parameters used for testing are:
1) limit:"" q:"" length:"" includeMetaData:"" - returns limited 10000 chars of a text file
2) limit:"50" q:"" length:"" includeMetaData:"" - returns limited 50 chars of a text file
3) limit:"50" q:"new" length:"" includeMetaData:"" - returns all matches for 'new', 50 chars are proceeded
4) limit:"20" q:"new" length:"5" includeMetaData:"" - returns all matches for 'new', String is no longer than 5, 20 chars are proceeded
5) limit:"" q:"This" length:"3" includeMetaData:"true" - returns all matches for 'Thi', 10000 chars are proceeded, Metadata is displayed
And hundreds of other combinations