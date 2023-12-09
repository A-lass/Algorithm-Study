#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dp[101][101];

int main()
{
    fastio;
    int n, ans = 0;
    cin >> n;

    dp[2][1] = 2; // 2층

    for(int i = 3;i <= n;i++)
        for(int j = 1;j <= n - 1;j++)
            dp[i][j] = (dp[i - 1][j] * 2 + dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 10007;

    for(int j = 1;j <= n - 1;j++)
        ans = (ans + dp[n][j]) % 10007;

    cout << ans << '\n';
}
