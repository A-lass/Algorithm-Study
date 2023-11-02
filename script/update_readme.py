import requests
import base64
import os

GH_TOKEN = os.environ.get('GH_TOKEN')

# 유저, 디렉토리 설정
users = ["ddingmin", "kjhonggg95", "ajsthfldu", "dydwo0740"]
dirs = ["baekjoon"]

url = f"https://api.github.com/repos/{os.environ.get('GITHUB_REPOSITORY')}/git/trees/main/"
solution_url = f"https://github.com/{os.environ.get('GITHUB_REPOSITORY')}/blob/main/"
boj_url = "https://www.acmicpc.net/problem/"
find_level_url = "https://solved.ac/api/v3/problem/show?problemId="
badge_url = "https://static.solved.ac/tier_small/"

# GitHub API 호출

problems = {}

for user in users:
    for dir in dirs:
        target_url = url + user + "/" + dir
        response = requests.get(url + user + "/" + dir).json()
        file_names = [item["name"] for item in response]

        for file_name in file_names:
            solved_number = file_name.split(".")[0]
            if solved_number not in problems:
                # boj 문제 정보
                data = requests.get(find_level_url + solved_number).json()

                # boj 레벨, 문제 이름
                level = str(data["level"])
                problem_title = data["titleKo"]

                problems[solved_number] = {"name": solved_number, "solved_users": {user: user},
                                           "problem_link": boj_url + solved_number,
                                           "solution_links": {user: solution_url + user + "/" + dir + "/" + file_name},
                                           "badge": (badge_url + level + ".svg"), "problem_title": problem_title}
            else:
                problems[solved_number]["solution_links"][user] = (solution_url + user + "/" + dir + "/" + file_name)
                problems[solved_number]["solved_users"][user] = user

readme = """# Algorithm-Study
solve problem everyday

## 🤖 스터디 멤버

<table>
 <tr>
    <td align="center">
     <a href="https://github.com/kjhonggg95"><img src="https://avatars.githubusercontent.com/kjhonggg95" width="130px;" alt=""></a>
     <br />
     <a href="https://github.com/kjhonggg95"><b>kjhonggg95</b></a>
    </td>
    <td align="center">
     <a href="https://github.com/dydwo0740"><img src="https://avatars.githubusercontent.com/dydwo0740" width="130px;" alt=""></a>
     <br />
     <a href="https://github.com/dydwo0740"><b>dydwo0740</b></a>
    </td>
    <td align="center">
     <a href="https://github.com/ajsthfldu"><img src="https://avatars.githubusercontent.com/ajsthfldu" width="130px;" alt=""></a>
     <br />
     <a href="https://github.com/ajsthfldu"><b>ajsthfldu</b></a>
    </td>
    <td align="center">
     <a href="https://github.com/ddingmin"><img src="https://avatars.githubusercontent.com/ddingmin" width="130px;" alt=""></a>
     <br />
     <a href="https://github.com/ddingmin"><b>ddingmin</b></a>
    </td>
  </tr>
  <tr height="50px">
        <td align="center">
            <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=kjhonggg" />
            <br />
            <a href="https://www.acmicpc.net/user/kjhonggg">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/kjhonggg">solved.ac</a>
        </td>
        <td align="center">
            <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=dydwo0740" />
            <br />
            <a href="https://www.acmicpc.net/user/dydwo0740">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/dydwo0740">solved.ac</a>
        </td>
        <td align="center">
            <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=inbloom" />
            <br />
            <a href="https://www.acmicpc.net/user/inbloom">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/inbloom">solved.ac</a>
        </td>
        <td align="center">
            <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=ddingmin" />
            <br />
            <a href="https://www.acmicpc.net/user/ddingmin">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/ddingmin">solved.ac</a>
        </td>
    </tr>
</table>

<br/>

## :heavy_check_mark: Solved
|        문제         |         난이도          |        풀이 링크         |  
| :-----: | :-----: | :-----: |
"""

for key in problems:
    line = f"""| <a href="{problems[key]["problem_link"]}" target="_blank">{key}번: {problems[key]["problem_title"]}</a> | <img height="25px" width="25px" src="{problems[key]["badge"]}"/> |"""
    for name in users:
        if name in problems[key]["solved_users"]:
            line += f"""<a href="{problems[key]["solution_links"][name]}">{name}</a> """
        else:
            line += name + " "
    line += " | \n"

    readme += line


update_url = f"https://api.github.com/repos/{os.environ.get('GITHUB_REPOSITORY')}/contents/README.md"
headers = {
    "Authorization": f"token {GH_TOKEN}"
}

# ReadMe 업데이트

response = requests.get(update_url, headers=headers)

if response.status_code == 200:
    # Update the content
    data = {
        "message": "Update Solved Content",
        # 생성된 파일 내용을 base64로 인코딩
        "content": base64.b64encode(readme.encode('UTF-8')).decode('ascii'),
        "sha": response.json()["sha"],
    }

    # Make a PUT request to update the README
    response = requests.put(update_url, json=data, headers=headers)

    if response.status_code == 200:
        print("README updated successfully.")
    else:
        print(f"Failed to update README. Status code: {response.status_code}")
        print(f"Response Content: {response.content}")
else:
    print(f"Failed to fetch README content. Status code: {response.status_code}")
    print(f"Response Content: {response.content}")
