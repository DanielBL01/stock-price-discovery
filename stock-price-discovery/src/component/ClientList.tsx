import { useEffect, useState } from "react";

interface ArticleSSE {
    title: string;
    author: string;
    summary: string;
    link: string;
    published_date: string;
}

export default function ClientListComponent() {
    const [articles, setArticles] = useState<ArticleSSE[]>([]);

    useEffect(() => {
        const eventSource = new EventSource("http://localhost:8080/stream/articles");

        eventSource.onopen = () => {
            console.log("SSE connection established");
        }
        eventSource.onmessage = (article) => {
            const articleData = JSON.parse(article.data);
            setArticles(((prevArticles) => [...prevArticles, articleData]));
            console.log("Received article", articleData);
        };

        eventSource.onerror = (error) => {
            console.log("Error:", error)
            console.log("Ready state:", eventSource.readyState);
        };

        return () => {
            eventSource.close();
        }
    }, []);

    return (
        <div>
            <h2>SSE Articles:</h2>
            {articles.map((article) => (
                <div key={article.link}>
                    <p>{article.summary}</p>
                </div>
            ))}
        </div>
    );
};