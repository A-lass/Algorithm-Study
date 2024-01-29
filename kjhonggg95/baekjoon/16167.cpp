#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

pii d[205][205]; // {거리, 거점 수}
int n, r, a, b, c, D, e;

int main()
{
    fastio;
    cin >> n >> r;

    for(int i = 1;i <= n;i++) for(int j = 1;j <= n;j++)
        d[i][j] = (i == j) ? make_pair(0.0, 0.0) : make_pair(1e9, 1e9);
            
    while(r--)
    {
        cin >> a >> b >> c >> D >> e;
        d[a][b] = {min(d[a][b].X, c + (e > 10 ? (e - 10) * D : 0)), 1};
    }

    for(int k = 1;k <= n;k++) for(int i = 1;i <= n;i++) for(int j = 1;j <= n;j++)
        if(d[i][k].X + d[k][j].X < d[i][j].X || d[i][k].X + d[k][j].X == d[i][j].X && d[i][k].Y + d[k][j].Y < d[i][j].Y)
            d[i][j] = {d[i][k].X + d[k][j].X, d[i][k].Y + d[k][j].Y};

    if(d[1][n].X == 1e9) cout << "It is not a great way." ;
    else cout << d[1][n].X << ' ' << d[1][n].Y + 1 << '\n';
}
