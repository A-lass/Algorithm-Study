#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int n, m, l, mx_c;
string s;

char arr[51][51];
int freq[26];
bool vis[51][51];

vector<pii> seq; // 방문 순서

// (1, 1) -> (n, m)

// 아이디 S 가 최대 몇 번 나올 수 있는지 구한다
int getMaxC()
{
    int cnt = 0;

    bool flag = true;

    while (flag)
    {
        for (auto e : s)
        {
            if (freq[e - 'a'] > 0)
            {
                freq[e - 'a']--;
            }
            else
            {
                flag = false;
                break;
            }
        }

        if (flag)
            cnt++;
    }

    return cnt;
}

pii getSeq(char c)
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (vis[i][j])
                continue;
            if (arr[i][j] == c)
            {
                vis[i][j] = true;
                return {i, j};
            }
        }
    }
    return {0, 0};
}

int main()
{
    fastio;
    string cmd = ""; // 명령어
    cin >> n >> m >> l;
    for (int i = 0; i < n; i++)
    {
        string t;
        cin >> t;
        for (int j = 0; j < m; j++)
        {
            arr[i][j] = t[j];
            freq[arr[i][j] - 'a']++;
        }
    }

    cin >> s;

    mx_c = getMaxC(); // 최대 강화 가능 횟수

    // 구현 순서
    // 1) 현재위치 -> 다음위치 이동
    // 2) Pick

    // case1) 강화할 수 있는 최대 횟수가 0인 경우
    // 출발 지점에서 도착 지점까지 이동하는 경로만 구하면 된다.

    // case2) 시작하자마자 아이템을 줍는 경우
    // 명령어에 P를 추가하고 다음 단계를 진행한다.

    // 도착지점에서 아이템을 주워야 하는지 여부
    bool last_pick = true;

    if (arr[0][0] != s[0]) // 출발 지점에서 아이템을 줍는 경우가 아니라면
        seq.push_back({0, 0}); // 출발 지점을 방문 순서에 넣어준다
    else if (mx_c == 0)// 최대 강화 회수가 0이면
    {
        seq.push_back({0, 0}); // 출발 지점을 방문 순서에 넣어준다
        last_pick = false; // 아이템을 주울 필요가 없으므로 마지막 줍기는 false
    }
    else if (arr[0][0] == s[0]) // 강화할 수 있으면서 출발지점에서 아이템을 줍는 경우
    {
        cmd += "P"; // 명령어에 P를 추가한 상태로 시작
    }

    // 아이템을 주워야하는 순서대로 방문 순서에 저장
    for (int i = 0; i < mx_c; i++)
        for (auto e : s)
            seq.push_back(getSeq(e));

    // 마지막 방문 지점이 포탈 위치가 아니라면
    if (seq.back().X != n - 1 || seq.back().Y != m - 1)
    {
        seq.push_back({n - 1, m - 1}); // 포탈 위치를 방문 순서 마지막에 넣어주고
        last_pick = false; // 마지막 줍기는 false
    }


    // i번째 방문지점 -> i + 1번째 방문지점으로 가는 명령어 갱신
    for (int i = 0; i < seq.size() - 1; i++)
    {
        // 행 이동
        int r = seq[i].X;
        int nr = seq[i + 1].X;

        if (r < nr)
        {
            for (int j = r; j < nr; j++)
                cmd += "D";
        }
        else if (r > nr)
        {
            for (int j = nr; j < r; j++)
                cmd += "U";
        }

        // 열 이동
        int c = seq[i].Y;
        int nc = seq[i + 1].Y;

        if (c < nc)
        {
            for (int j = c; j < nc; j++)
                cmd += "R";
        }
        else if (c > nc)
        {
            for (int j = nc; j < c; j++)
                cmd += "L";
        }

        // 줍기
        cmd += "P";
    }

    // 마지막에 줍는 경우가 아니라면 명령어 맨 마지막에 추가된 P를 빼준다
    if (!last_pick)
        cmd.pop_back();

    cout << mx_c << ' ' << cmd.size() << ' ' << cmd << '\n';
}
