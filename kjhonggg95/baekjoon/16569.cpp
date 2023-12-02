#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int h[101][101]; // 고도
int t[101][101]; // 화산쇄설류로 뒤덮이는 시간
bool volcano[101][101];
int arrive[101][101];
bool vis[101][101];

int n, m, v, x, y;

int main()
{
    fastio;
    cin >> n >> m >> v >> x >> y;

    // 화산의 고도
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++)
        {
            cin >> h[i][j];
            t[i][j] = 1e9;
        }

    queue<pair<pii, int>> q; // {위치, 시간}

    while (v--)
    {
        int a, b, c;
        cin >> a >> b >> c;
        volcano[a][b] = true;
        t[a][b] = c;
        q.push({{a, b}, c});

        while (!q.empty())
        {
            auto cur = q.front().X;
            auto time = q.front().Y;
            q.pop();

            for (int dir = 0; dir < 4; dir++)
            {
                int nx = cur.X + dx[dir];
                int ny = cur.Y + dy[dir];

                if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
                if (t[nx][ny] <= t[cur.X][cur.Y] + 1) continue;
                t[nx][ny] = time + 1;
                q.push({{nx, ny}, time + 1});
            }
        }
    }

    q.push({{x, y}, 0}); // 재승 위치, 0초부터 시작
    vis[x][y] = true;
    arrive[x][y] = 0;

    while (!q.empty())
    {
        auto cur = q.front().X;
        auto time = q.front().Y;
        q.pop();

        for (int dir = 0; dir < 4; dir++)
        {
            int nx = cur.X + dx[dir];
            int ny = cur.Y + dy[dir];

            if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
            if (vis[nx][ny] || volcano[nx][ny] || time + 1 >= t[nx][ny]) continue;

            vis[nx][ny] = true;
            arrive[nx][ny] = time + 1;
            q.push({{nx, ny}, time + 1});
        }
    }

    int maxh = h[x][y], mint = 1e9;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            if (!vis[i][j]) continue;
            maxh = max(maxh, h[i][j]);
        }
    }

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++)
            if (vis[i][j] && h[i][j] == maxh)
                mint = min(mint, arrive[i][j]);

    cout << maxh << ' ' << mint << '\n';
}
