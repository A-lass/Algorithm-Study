#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, m, l, k, mx;

int main()
{
    fastio;
    cin >> n >> m >> l >> k;
    vector<pii> star(k);
    for(int i = 0;i<k;i++)
        cin >> star[i].X >> star[i].Y;

    for(auto& a : star)
    {
        for(auto& b : star)
        {
            int x = a.X, y = b.Y, cnt = 0;
            for(auto& e : star)
                if(e.X >= x && e.X <= x + l && e.Y >= y && e.Y <= y + l) cnt++;
            mx = max(mx, cnt);
        }
    }

    cout << k - mx << '\n';
}   
