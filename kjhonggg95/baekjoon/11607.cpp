#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

char arr[501][501];
bool vis[501][501];

int main()
{
    fastio;
    int n, m;
    cin >> n >> m;
    for(int i = 0;i<n;i++)
        for(int j = 0;j<m;j++)
            cin >> arr[i][j];

    queue<pair<pii, int>> q;

    q.push({{0,0}, 0});
    vis[0][0] = true;

    while(!q.empty())
    {
        auto cur = q.front().X;
        auto cnt = q.front().Y;
        q.pop();

        if(cur.X == n - 1 && cur.Y == m - 1)
        {
            cout << cnt << '\n';
            return 0;
        }

        for(int dir = 0;dir<4;dir++)
        {
            int nx = cur.X + dx[dir] * (arr[cur.X][cur.Y] - '0');
            int ny = cur.Y + dy[dir] * (arr[cur.X][cur.Y] - '0');

            if(nx < 0 || nx >= n || ny < 0 || ny >=m) continue;
            if(vis[nx][ny]) continue;

            q.push({{nx,ny}, cnt + 1});
            vis[nx][ny] = true;
        }
    }

    cout << "IMPOSSIBLE" << '\n';
}