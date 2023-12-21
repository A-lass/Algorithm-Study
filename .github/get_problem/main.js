const axios = require('axios');
const cheerio = require('cheerio');
const { Octokit } = require('@octokit/core');

async function getProblemUrl() {
  try {
    const response = await axios.get('https://solved.ac/search?query=*g5..g3+s%23100..+%21%23math+%21%23geometry+%21%23number_theory+%21%40kjhonggg+%21%40ddingmin+%21%40dydwo0740+%21%40inbloom+%21%40pon06061&sort=random&direction=asc&page=1');
    const html = response.data;
    const $ = cheerio.load(html);

    const elementContent = $('#__next > div > div:nth-child(4) > div.css-qijqp5 > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > div > span > a > span').text();

    return `https://www.acmicpc.net/problem/${elementContent}`;
  } catch (error) {
    throw new Error('Failed to fetch the problem URL');
  }
}

async function createGitHubIssue(url) {
  try {
    const octokit = new Octokit({ auth: process.env.GITHUB_TOKEN });
    const [owner, repo] = process.env.GITHUB_REPOSITORY.split('/');
    const today = new Date();

    const options = { timeZone: 'Asia/Seoul', year: 'numeric', month: '2-digit', day: '2-digit' };
    const dateFormatter = new Intl.DateTimeFormat('ko-KR', options);
    const formattedDate = dateFormatter.format(today);

    const issueResponse = await octokit.request('POST /repos/:owner/:repo/issues', {
      owner,
      repo,
      title: formattedDate,
      body: url,
    });

    console.log('GitHub Issue created:', issueResponse.data.html_url);
  } catch (error) {
    console.error('Error creating GitHub Issue:', error.message);
  }
}

async function sendDiscordMessage(messageContent) {
  try {
    const webhookUrl = process.env.DISCORD_ALGO;
    const message = {
      content: messageContent,
      username: '오늘의 알고리즘',
    };

    const response = await axios.post(webhookUrl, message);
    console.log('Discord message sent successfully:', response.data);
  } catch (error) {
    console.error('Error sending Discord message:', error.message);
  }
}

async function run() {
  try {
    const problemUrl = await getProblemUrl();
    const formattedDate = new Intl.DateTimeFormat('ko-KR', { timeZone: 'Asia/Seoul', year: 'numeric', month: '2-digit', day: '2-digit' }).format(new Date());
    
    // GitHub Issue 생성
    await createGitHubIssue(problemUrl);

    // Discord 메시지 전송
    const discordMessageContent = `${formattedDate}\n${problemUrl}`;
    await sendDiscordMessage(discordMessageContent);
  } catch (error) {
    console.error('Error:', error.message);
  }
}

run();
