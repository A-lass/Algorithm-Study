#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dx[6] = {-1, -1, 0, 0, 1, 1};
int dy[6] = {0, 1, -1, 1, 0, 1};

int arr[103][103];
bool vis[103][103];

int main()
{
    fastio;
    int r, c;
    cin >> c >> r;
    for (int i = 1; i <= r; i++)
        for (int j = 1; j <= c; j++)
            cin >> arr[i][j];

    queue<pii> q;
    q.push({0, 0});
    vis[0][0] = true;

    int cnt = 0;

    while (!q.empty())
    {
        auto cur = q.front();
        q.pop();

        for (int dir = 0; dir < 6; dir++)
        {
            int nx = cur.X + dx[dir];
            int ny = cur.Y + dy[dir];

            if(cur.X % 2 == 0 && dx[dir] != 0) ny--;

            cnt += (arr[nx][ny] == 1);

            if (nx < 0 || nx > r + 1 || ny < 0 || ny > c + 1) continue;
            if (vis[nx][ny] || arr[nx][ny] == 1) continue;
      
            q.push({nx, ny});
            vis[nx][ny] = true;
        }
    }

    cout << cnt << '\n';
}
