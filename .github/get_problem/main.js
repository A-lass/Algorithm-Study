const axios = require('axios');
const cheerio = require('cheerio');
const { Octokit } = require('@octokit/core');

async function run() {
  try {
    // 웹 페이지에서 HTML 가져오기
    const response = await axios.get('https://solved.ac/search?query=*g5..g3+s%23100..+%21%23math+%21%23geometry+%21%23number_theory+%21%40kjhonggg+%21%40ddingmin+%21%40dydwo0740+%21%40inbloom+%21%40pon06061&sort=random&direction=asc&page=1');
    const html = response.data;

    // Cheerio를 사용하여 HTML 파싱
    const $ = cheerio.load(html);

    // 원하는 요소 선택
    const elementContent = $('#__next > div > div:nth-child(4) > div.css-qijqp5 > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > div > span > a > span').text();


    console.log(`Content: ${elementContent}`);
      // 날짜
      const today = new Date();
  
      // UTC+9 설정
      const options = { timeZone: 'Asia/Seoul', year: 'numeric', month: '2-digit', day: '2-digit' };
      const dateFormatter = new Intl.DateTimeFormat('ko-KR', options);
  
      // 날짜를 문자열로 변환
      const formattedDate = dateFormatter.format(today);
  
      // GitHub 이슈 생성
      const octokit = new Octokit({ auth: process.env.GITHUB_TOKEN });
  
      // GITHUB_REPOSITORY 환경 변수에서 owner와 repo 정보 추출
      const [owner, repo] = process.env.GITHUB_REPOSITORY.split('/');
  
      // GitHub 이슈에 직접 문자열 전달
      const issueResponse = await octokit.request('POST /repos/:owner/:repo/issues', {
        owner,
        repo,
        title: formattedDate,
        body: `https://www.acmicpc.net/problem/${elementContent}`,
      });
  
      console.log('Issue created:', issueResponse.data.html_url);
    } catch (error) {
      console.error('Error:', error.message);
    }
}

run();
