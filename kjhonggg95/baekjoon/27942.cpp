#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

#define R 1
#define L 2
#define D 3
#define U 4

int n, upR, downR, leftC, rightC, tot;
short arr[3001][3001];
char dirarr[5] = {' ', 'R', 'L', 'D', 'U'};

string cmd = "";

int main()
{
    fastio;
    cin >> n;
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            cin >> arr[i][j];

    upR = leftC = n / 2;
    downR = rightC = n / 2 + 1;

    // 상하좌우 순으로 우선이므로
    // 우좌하상 순으로 탐색

    while (1)
    {
        int mx_sum = 1; // 먹을 수 있는 최대 양분
        int dir = 0;    // 먹는 방향

        // 우
        int sum = 0;
        if (rightC + 1 <= n)
            for (int i = upR; i <= downR; i++)
                sum += arr[i][rightC + 1];

        if (sum >= mx_sum)
        {
            mx_sum = sum;
            dir = R;
        }

        // 좌
        sum = 0;
        if (leftC - 1 > 0)
            for (int i = upR; i <= downR; i++)
                sum += arr[i][leftC - 1];

        if (sum >= mx_sum)
        {
            mx_sum = sum;
            dir = L;
        }

        // 하
        sum = 0;
        if (downR + 1 <= n)
            for (int i = leftC; i <= rightC; i++)
                sum += arr[downR + 1][i];

        if (sum >= mx_sum)
        {
            mx_sum = sum;
            dir = D;
        }

        // 상
        sum = 0;
        if (upR - 1 > 0)
            for (int i = leftC; i <= rightC; i++)
                sum += arr[upR - 1][i];

        if (sum >= mx_sum)
        {
            mx_sum = sum;
            dir = U;
        }

        if (dir == 0)
            break;
        else if (dir == R)
            rightC++;
        else if (dir == L)
            leftC--;
        else if (dir == D)
            downR++;
        else if (dir == U)
            upR--;
        
        cmd += dirarr[dir];
        tot += mx_sum;
    }

    cout << tot << '\n' << cmd << '\n';
}
