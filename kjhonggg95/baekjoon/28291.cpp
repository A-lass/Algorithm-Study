#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

#define DUST 1
#define BLOCK 2
#define LAMP 3

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int r, c, rr, cc, n;

int arr[55][55];   // 레드스톤 종류
int power[55][55]; // 가지고 있는 전기신호

set<pii> lamp_pos;

int main()
{
    fastio;
    cin >> c >> r >> n;

    queue<pair<pii, int>> q; // {위치, 전기신호};

    for (int i = 0; i < n; i++)
    {
        string s;
        cin >> s >> cc >> rr;

        if (s == "redstone_dust")
            arr[rr][cc] = DUST;
        else if (s == "redstone_block")
        {
            arr[rr][cc] = BLOCK;
            q.push({{rr, cc}, 16});
        }
        else if (s == "redstone_lamp")
        {
            arr[rr][cc] = LAMP;
            lamp_pos.insert({rr, cc});
        }
    }

    while (!q.empty())
    {
        auto cur = q.front().X;
        auto sig = q.front().Y;
        q.pop();

        if (sig == 0) continue;

        power[cur.X][cur.Y] = sig;

        for (int dir = 0; dir < 4; dir++)
        {
            int nx = cur.X + dx[dir];
            int ny = cur.Y + dy[dir];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if (arr[nx][ny] == 0 || sig - 1 <= power[nx][ny]) continue;

            if (arr[nx][ny] == DUST)
                q.push({{nx, ny}, sig - 1});
            else if (arr[nx][ny] == LAMP)
                power[nx][ny] = sig - 1;
        }
    }

    for (auto e : lamp_pos)
        if (power[e.X][e.Y] == 0)
        {
            cout << "failed" << '\n';
            return 0;
        }

    cout << "success" << '\n';
    return 0;
}
