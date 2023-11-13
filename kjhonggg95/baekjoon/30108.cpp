#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;


int main()
{
    fastio;
    int n;
    cin >> n;
    vector<int> v(n);
    for(int i = 0;i<n - 1;i++)
    {
        int p;
        cin >> p;
    }

    for(int i = 0;i<n;i++)
        cin >> v[i];
    sort(v.begin(), v.end(), greater<>());
    ll ans = 0;
    for(auto e : v)
    {
        ans += e;
        cout << ans << '\n';
    }
}
