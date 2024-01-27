#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

bool pos[5005][5005];

int main()
{
    fastio;
    int n;
    cin >> n;
    vector<int> xx(n);
    vector<int> yy(n);
    for(int i = 0;i < n;i++)
        cin >> xx[i] >> yy[i];
    
    vector<int> x(xx);
    vector<int> y(yy);

    sort(x.begin(), x.end());
    sort(y.begin(), y.end());

    x.erase(unique(x.begin(), x.end()), x.end());
    y.erase(unique(y.begin(), y.end()), y.end());

    vector<pii> v(n);

    for(int i = 0;i < n;i++)
    {
        int xxx = lower_bound(x.begin(), x.end(), xx[i]) - x.begin();
        int yyy = lower_bound(y.begin(), y.end(), yy[i]) - y.begin();
        pos[xxx][yyy] = true;
        v[i] = {xxx, yyy};
    }

    int cnt = 0;

    for(int i = 0;i < n;i++)
    {
        for(int j = 0;j < n;j++)
        {
            if(v[i].X >= v[j].X || v[i].Y >= v[j].Y) continue;
            if(pos[v[i].X][v[j].Y] && pos[v[j].X][v[i].Y]) cnt++;
        }
    }
   
    cout << cnt << '\n';
}   
