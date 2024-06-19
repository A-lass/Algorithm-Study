import axios from "axios";
import {Octokit} from "octokit";
import * as cheerio from "cheerio";

async function getProblemUrl() {
    const response = await axios.get('https://solved.ac/search?query=*g3..p5+s%23200..+%21%23math+%21%23geometry+%21%23number_theory+%21%40dydwo0740+%21%40inbloom+w%3Ffalse+%25ko&sort=random&direction=asc&page=1');
    const html = response.data;
    const $ = cheerio.load(html);
    const problemId = $('#__next > div.css-1shh4uc > div:nth-child(4) > div.css-qijqp5 > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > div > span > span > a').text();
    if (!problemId) {
        throw new Error('Failed to fetch the problem URL');
    }

    console.log('Got problem id: ', problemId)
    return `https://www.acmicpc.net/problem/${problemId}`;
}

async function createGitHubIssue(url) {
    const octokit = new Octokit({auth: process.env.GITHUB_TOKEN});
    const [owner, repo] = process.env.GITHUB_REPOSITORY.split('/');
    const today = new Date();

    const options = {timeZone: 'Asia/Seoul', year: 'numeric', month: '2-digit', day: '2-digit'};
    const dateFormatter = new Intl.DateTimeFormat('ko-KR', options);
    const formattedDate = dateFormatter.format(today);

    const issueResponse = await octokit.request('POST /repos/:owner/:repo/issues', {
        owner,
        repo,
        title: formattedDate,
        body: url,
    });
    console.log('GitHub Issue created:', issueResponse.data.html_url);
}

async function sendDiscordMessage(messageContent) {
    const webhookUrl = process.env.DISCORD_ALGO;
    const message = {
        content: messageContent,
        username: '오늘의 알고리즘',
    };

    const response = await axios.post(webhookUrl, message);
    console.log('Discord message sent successfully:', response.data);
}

const formattedDate = new Intl.DateTimeFormat('ko-KR', {
    timeZone: 'Asia/Seoul',
    year: 'numeric',
    month: '2-digit',
    day: '2-digit'
}).format(new Date());

const problemUrl = await getProblemUrl();
console.log(problemUrl)
await createGitHubIssue(problemUrl);
await sendDiscordMessage(`${formattedDate}\n${problemUrl}`);
