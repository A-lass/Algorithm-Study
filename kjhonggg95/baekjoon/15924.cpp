#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

const int mod =  1000000009;

char arr[3005][3005];
ll dp[3005][3005];

int main()
{
    fastio;
    int n, m;
    cin >> n >> m;
    for(int i = 1;i <= n;i++)
    {
        string s;
        cin >> s;
        for(int j = 1;j <= m;j++)
            arr[i][j] = s[j - 1];
    }

    for(int i = 1;i <= n;i++)
    {
        for(int j = 1;j <= m;j++)
        {
            dp[i][j]++; // 해당 위치에서 탐색 시작
            if(arr[i][j] == 'E') // 오른쪽
            {
                dp[i][j + 1] = (dp[i][j + 1] + dp[i][j]) % mod;
            }
            else if(arr[i][j] == 'S') // 아래쪽
            {
                dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % mod;
            }
            else if(arr[i][j] == 'B') // 오른쪽, 아래쪽
            {
                dp[i][j + 1] = (dp[i][j + 1] + dp[i][j]) % mod;
                dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % mod;
            }
        }
    }

    cout << dp[n][m] % mod << '\n';
}
