#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<ll, ll>;

// 정점 8개

ll dist[10][10];

vector<pii> v;

int main()
{
    fastio;
    vector<pii> v(9);
    for(int i = 1;i <= 8;i++)
        for(int j = 1;j <= 8;j++)
            dist[i][j] = LLONG_MAX;

    cin >> v[1].X >> v[1].Y >> v[2].X >> v[2].Y;

    for(int i = 3;i <= 7;i += 2)
    {
        cin >> v[i].X >> v[i].Y >> v[i + 1].X >> v[i + 1].Y;
        dist[i][i + 1] = dist[i + 1][i] = 10;
    }

    for(int i = 1;i <= 8;i++)
        for(int j = 1;j <= 8;j++)
            dist[i][j] = min(dist[i][j], abs(v[i].X - v[j].X) + abs(v[i].Y - v[j].Y));

    for(int k = 1;k <= 8;k++)
        for(int i = 1;i <= 8;i++)
            for(int j = 1;j <= 8;j++)
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);

    cout << dist[1][2] << '\n';
}
