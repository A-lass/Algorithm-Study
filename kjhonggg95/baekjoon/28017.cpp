#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dp[505][505];

int n, m, ans = 1e9;

int main()
{
    fastio;
    cin >> n >> m;

    for(int i = 1;i <= n;i++)
    {
        for(int j = 1;j <= m;j++)
        {
            cin >> dp[i][j];
            int min_prv = 1e9;
            for(int k = 1;k <= m;k++)
            {
                if(j == k) continue;
                min_prv = min(min_prv, dp[i - 1][k]);
            }
            dp[i][j] += min_prv;
            if(i == n) ans = min(ans, dp[i][j]);
        }
    }

    cout << ans << '\n';
}
