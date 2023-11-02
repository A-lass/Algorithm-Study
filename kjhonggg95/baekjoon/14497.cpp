#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int arr[301][301];
int d[301][301];

int main()
{
    fastio;
    int n, m, x1, y1, x2, y2;
    cin >> n >> m >> x1 >> y1 >> x2 >> y2;
    x1--; y1--; x2--; y2--;
    for (int i = 0; i < n; i++)
    {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++)
        {
            if (s[j] == '#' || s[j] == '*' || s[j] == '0')
                arr[i][j] = 0;
            else
                arr[i][j] = 1;
            d[i][j] = 1e9;
        }
    }

    priority_queue<pair<int, pii>, vector<pair<int, pii>>, greater<pair<int, pii>>> pq;

    d[x1][y1] = 0;
    pq.push({d[x1][y1], {x1, y1}});

    while (!pq.empty())
    {
        auto w = pq.top().X;
        auto cur = pq.top().Y;
        pq.pop();

        if (d[cur.X][cur.Y] != w)
            continue;

        for (int dir = 0; dir < 4; dir++)
        {
            int nx = cur.X + dx[dir];
            int ny = cur.Y + dy[dir];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (d[nx][ny] <= d[cur.X][cur.Y] + arr[nx][ny]) continue;
            d[nx][ny] = d[cur.X][cur.Y] + arr[nx][ny];
            pq.push({d[nx][ny], {nx, ny}});
        }
    }

    cout << d[x2][y2] + 1 << '\n';
}
