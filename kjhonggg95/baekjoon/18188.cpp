#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int h, w, n;
char arr[21][21];
char dir[21][2];

pii st;
string s, ans = "NO";

void dfs(int curR, int curC, int step, string cmd)
{
    if (step > n || curR <= 0 || curR > h || curC <= 0 || curC > w || arr[curR][curC] == '@')
        return;

    if (arr[curR][curC] == 'Z')
    {
        ans = "YES\n" + cmd;
        return;
    }

    for (int i = 0; i < 2; i++)
    {
        if (dir[step][i] == 'W')
            dfs(curR - 1, curC, step + 1, cmd + 'W');
        else if (dir[step][i] == 'A')
            dfs(curR, curC - 1, step + 1, cmd + 'A');
        else if (dir[step][i] == 'S')
            dfs(curR + 1, curC, step + 1, cmd + 'S');
        else if (dir[step][i] == 'D')
            dfs(curR, curC + 1, step + 1, cmd + 'D');
    }
}

int main()
{
    fastio;
    cin >> h >> w;
    for (int i = 1; i <= h; i++)
    {
        cin >> s;
        for (int j = 1; j <= w; j++)
        {
            arr[i][j] = s[j - 1];
            if (arr[i][j] == 'D')
                st = {i, j};
        }
    }

    cin >> n;
    
    for (int i = 0; i < n; i++)
        cin >> dir[i][0] >> dir[i][1];

    dfs(st.X, st.Y, 0, "");

    cout << ans << '\n';
}
