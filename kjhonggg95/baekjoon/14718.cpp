#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int str[101];
int dex[101];
int inte[101];

int main()
{
    fastio;
    int n, K, ans = 1e9;
    cin >> n >> K;
    for(int i = 0;i<n;i++)
        cin >> str[i] >> dex[i] >> inte[i];

    for(int i = 0;i<n;i++)
    {
        for(int j = 0;j<n;j++)
        {   
            for(int k = 0;k<n;k++)
            {
                int cnt = 0;
                for(int l = 0;l<n;l++)
                    if(str[i] >= str[l] && dex[j] >= dex[l] && inte[k] >= inte[l]) cnt++;
                if(cnt >= K)
                    ans = min(ans, str[i] + dex[j] + inte[k]);
            }
        }
    }

    cout << ans << '\n';
}
