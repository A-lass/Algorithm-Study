#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int arr[105][105];
int dp[105][105];

int main()
{
    fastio;
    int n, m, x, ans = 1e9;
    cin >> n >> m;
    for(int i = 1; i <= n;i++)
        for(int j = 1;j <= m;j++)
            cin >> arr[i][j];

    for(int i = 1; i <= n;i++)
        for(int j = 1;j <= m;j++)
        {
            cin >> x;
            arr[i][j] = (arr[i][j] - x) * (arr[i][j] - x);
            if(i == 1) dp[i][j] = arr[i][j];
        }

    for(int i = 2;i <= n;i++)
    {
        for(int j = 1;j <= m;j++)
        {
            if(m >= 2)
            {
                if(j == 1)  
                    dp[i][j] = min(dp[i - 1][j], dp[i - 1][j + 1]) + arr[i][j];
                else if(j == m) 
                    dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
                else 
                    dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]}) + arr[i][j];
            }
            else
                dp[i][j] = dp[i - 1][j] + arr[i][j];
        }
    }

    for(int j = 1;j <= m;j++)
        ans = min(ans, dp[n][j]);
    cout << ans << '\n';
}
