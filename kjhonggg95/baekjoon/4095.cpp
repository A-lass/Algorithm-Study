#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dp[1005][1005];

int main()
{
    fastio;
    int n, m;
    while(cin >> n >> m)
    {
        if(n == 0 && m == 0) return 0;
        memset(dp, 0, sizeof(dp));

        int ans = 0;

        for(int i = 1;i <= n;i++)
        {
            for(int j = 1;j <= m;j++)
            {
                cin >> dp[i][j];
                if(dp[i][j] == 0) continue;
                dp[i][j] = min({dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]}) + 1;
                ans = max(ans, dp[i][j]);
            }
        }

        cout << ans << '\n';
    }
}
