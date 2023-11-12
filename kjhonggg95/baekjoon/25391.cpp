#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, m, k, a, b, cnt;
ll ans;

bool comp(pair<pii, bool> &a, pair<pii, bool> &b)
{
    return a.X.Y > b.X.Y;
}

int main()
{
    fastio;
    cin >> n >> m >> k;

    vector<pair<pii, bool>> v;
    for (int i = 0; i < n; i++)
    {
        cin >> a >> b;
        v.push_back({{a, b}, false});
    }

    // 심판 점수 순 정렬
    sort(v.begin(), v.end(), comp);
    for (int i = 0; i < k; i++)
    {
        ans += v[i].X.X;
        v[i].Y = true;
    }

    // 주최자 점수 순 정렬
    sort(v.begin(), v.end(), greater<>());

    for (int i = 0; m ; i++)
    {
        if (v[i].Y == true) continue;
        ans += v[i].X.X;
        m--;
    }

    cout << ans << '\n';
}
