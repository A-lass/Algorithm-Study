#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int arr[101][101];

int main()
{
    fastio;
    int n;
    for (int t = 1;;t++)
    {
        cin >> n;
        if (n == 0)
            return 0;

        memset(arr, 0, sizeof(arr));
        int mx = -1e9;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cin >> arr[i][j];

        // ■ ■ ■ ■
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n - 3; j++)
                mx = max(mx, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i][j + 3]);

        // ■
        // ■
        // ■
        // ■
        for (int i = 0; i < n - 3; i++)
            for (int j = 0; j < n; j++)
                mx = max(mx, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 3][j]);

        // ■ ■
        //   ■ ■
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 2; j++)
                mx = max(mx, arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j + 2]);

        //   ■
        // ■ ■
        // ■
        for (int i = 0; i < n - 2; i++)
            for (int j = 0; j < n - 1; j++)
                mx = max(mx, arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j]);

        // ■ ■ ■
        //     ■
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 2; j++)
                mx = max(mx, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 2]);

        //   ■
        //   ■
        // ■ ■
        for (int i = 0; i < n - 2; i++)
            for (int j = 0; j < n - 1; j++)
                mx = max(mx, arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j + 1] + arr[i + 2][j]);

        // ■
        // ■ ■ ■
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 2; j++)
                mx = max(mx, arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2]);

        // ■ ■
        // ■
        // ■
        for (int i = 0; i < n - 2; i++)
            for (int j = 0; j < n - 1; j++)
                mx = max(mx, arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 2][j]);

        // ■ ■ ■
        //   ■
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 2; j++)
                mx = max(mx, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1]);

        //   ■
        // ■ ■
        //   ■
        for (int i = 0; i < n - 2; i++)
            for (int j = 0; j < n - 1; j++)
                mx = max(mx, arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j + 1]);

        //   ■
        // ■ ■ ■
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 2; j++)
                mx = max(mx, arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2]);

        // ■
        // ■ ■
        // ■
        for (int i = 0; i < n - 2; i++)
            for (int j = 0; j < n - 1; j++)
                mx = max(mx, arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j]);

        // ■ ■
        // ■ ■
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 1; j++)
                mx = max(mx, arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1]);

        cout << t << ". " << mx << '\n';
    }
}
