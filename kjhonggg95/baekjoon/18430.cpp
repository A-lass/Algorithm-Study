#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, m, ans;

int dx[4] = {1, 1, -1, -1};
int dy[4] = {1, -1, 1, -1};

int arr[5][5];
bool vis[5][5];

void dfs(int r, int c, int sum)
{
    ans = max(ans, sum);

    if (c == m)
    {
        r++;
        c = 0;
        if (r == n) return;
    }

    if (!vis[r][c])
    {
        for (int dir = 0; dir < 4; dir++)
        {
            int nx = r + dx[dir];
            int ny = c + dy[dir];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (vis[nx][c] || vis[r][ny]) continue;

            vis[r][c] = vis[nx][c] = vis[r][ny] = true;
            dfs(r, c + 1, sum + arr[r][c] * 2 + arr[nx][c] + arr[r][ny]);
            vis[r][c] = vis[nx][c] = vis[r][ny] = false;
        }
    }

    dfs(r, c + 1, sum);
}

int main()
{
    fastio;
    cin >> n >> m;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            cin >> arr[i][j];

    dfs(0, 0, 0);
    cout << ans << '\n';
}
