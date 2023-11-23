#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

ll dp[101][3][2];
int pre[101];

// [i][j][k] : i번째날에 j파스타를 먹는 경우의 수
// k : 0 -> 전날 다른 종류를 먹은 상태
// k : 1 -> 이틀 연속으로 먹은 상태

int main()
{
    fastio;
    int n, k;
    cin >> n >> k;

    while (k--)
    {
        int a, b;
        cin >> a >> b;
        pre[a] = b;
    }

    if(pre[1] != 0) dp[1][pre[1] - 1][0] = 1;
    else dp[1][0][0] = dp[1][1][0] = dp[1][2][0] = 1;

    for (int i = 2; i <= n; i++)
    {
        if(pre[i] != 0)
        {  
            int k = pre[i] - 1;
            dp[i][k][0] = (dp[i-1][(k + 1) % 3][0] + dp[i-1][(k + 1) % 3][1] + dp[i-1][(k + 2) % 3][0] + dp[i-1][(k + 2) % 3][1]) % 10000;
            dp[i][k][1] = dp[i-1][k][0];
        }
        else
        {
            for(int k = 0;k<3;k++)
            {
                dp[i][k][0] = (dp[i-1][(k + 1) % 3][0] + dp[i-1][(k + 1) % 3][1] + dp[i-1][(k + 2) % 3][0] + dp[i-1][(k + 2) % 3][1]) % 10000;
                dp[i][k][1] = dp[i-1][k][0];
            }
        }
    }

    cout << (dp[n][0][0] + dp[n][0][1] + dp[n][1][0] + dp[n][1][1] + dp[n][2][0] + dp[n][2][1]) % 10000 << '\n';
}
