#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int n, m;

bool vis[3][3];
char arr[3][3];

void dfs(int r, int c, int res, int cnt, char prev, vector<pii> v)
{
    v.push_back({r, c});

    // 현재 칸이 숫자인 경우
    if (arr[r][c] >= '1' && arr[r][c] <= '9')
    {
        if (prev == '+')
            res += (arr[r][c] - '0');
        else if (prev == '-')
            res -= (arr[r][c] - '0');

        cnt++;

        if (cnt == m)
        {
            if (res == n)
            {
                cout << 1 << '\n';
                for (auto e : v)
                    cout << e.X << ' ' << e.Y << '\n';
                exit(0);
            }

            return;
        }
    }
    else // 현재 칸이 연산자인 경우
        prev = arr[r][c];

    for (int dir = 0; dir < 4; dir++)
    {
        int nx = r + dx[dir];
        int ny = c + dy[dir];

        if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3)
            continue;
        if (vis[nx][ny])
            continue;

        vis[nx][ny] = true;
        dfs(nx, ny, res, cnt, prev, v);
        vis[nx][ny] = false;
    }
}

int main()
{
    fastio;
    cin >> n >> m;
    for (int i = 0; i < 3; i++)
    {
        string s;
        cin >> s;
        for (int j = 0; j < 3; j++)
            arr[i][j] = s[j];
    }

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (arr[i][j] >= '0' && arr[i][j] <= '9')
            {
                vector<pii> v;
                vis[i][j] = true;
                dfs(i, j, arr[i][j] - '0', 0, 'x', v);
                vis[i][j] = false;
            }
        }
    }

    cout << 0 << '\n';
}
