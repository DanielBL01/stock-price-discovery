# Stock Price Discovery

## Context
When dealing with stocks, there is a concept called “price discovery”, which is when breaking news comes in and stock 
prices react. As stockholders and investors, It’s important to process as much information as we can, not only of the
stocks that we own but also of other companies, their performances and metrics to possibly expand our portfolios to
make a profit.

However, it’s not reasonable to actively go out and search for this information in today’s day and age where
information is everywhere, which can be overwhelming.

Stock Price Discovery is built and intended to be a one-stop shop which runs multiple microservices to collect & ingest
data in real-time, provide summaries of articles for faster consumption of critical information and importantly,
gives users a clean, simplistic user interface which information can be easily categorized and labeled.

## Technologies (Current & Updating)
### Frontend
- TypeScript
- React
### Backend
- Java
- Spring Boot
- Apache Kafka
	- Data pipeline to move data producers from one RESTful API to stream from another RESTful API
- PostgreSQL
- Redis
	- Currently using Redis as a "link" field cache to avoid sending duplicate articles referencing the same link in article sse data
- Google Guava
	- Provides richer APIs for common Java libraries
- Server-Sent Events (SSE)
	- Unidirectional protocol (server to client) to enable real-time communication
### Cloud/Infrastructure
- Docker
	- Currently have docker-compose.yml for Apache Zookeeper, Broker, Redis and PostgreSQL. NOTE to self: Ran into headaches because local postgres server was running. Make sure when using docker, there are no local counterparts running as a background process locally on computer
### Dev Tools
- Vite
	- Better than create-react-app as it uses esbuild (written in Go) for better performance and offers a more minimal template to create single page applications
- Maven
